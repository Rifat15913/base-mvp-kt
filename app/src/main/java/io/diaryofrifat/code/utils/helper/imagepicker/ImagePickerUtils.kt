package io.diaryofrifat.code.utils.helper.imagepicker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Parcelable
import android.provider.MediaStore
import androidx.exifinterface.media.ExifInterface
import java.io.File
import java.io.FileNotFoundException
import java.util.*

// TODO: Test this class
object ImagePickerUtils {
    // Constants
    const val REQUEST_CODE_PICK_IMAGE = 15913
    private const val DEFAULT_MIN_WIDTH_QUALITY = 400 // Minimum pixels
    private const val TEMP_ORIGINAL_IMAGE = "tempOriginalImage.jpg"
    private const val PICKER_TITLE = "Pick Image"

    /**
     * TODO: Write comment here
     * */
    fun pickImage(activity: Activity) {
        val imagePickingIntent = getImagePickingIntent(activity)

        if (imagePickingIntent != null) {
            activity.startActivityForResult(imagePickingIntent, REQUEST_CODE_PICK_IMAGE)
        }
    }

    /**
     * TODO: Write comment here
     * */
    fun getImagePickingIntent(context: Context): Intent? {
        var chooserIntent: Intent? = null

        var intentList: MutableList<Intent> = ArrayList()

        val pickPhotoIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePhotoIntent.putExtra("return-data", true)

        val emptyTempFile = getEmptyTempFileForImage(context)
                ?: return null

        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(emptyTempFile))

        intentList = addIntentsToList(context, intentList, pickPhotoIntent)
        intentList = addIntentsToList(context, intentList, takePhotoIntent)

        if (intentList.size > 0) {
            chooserIntent = Intent.createChooser(intentList.removeAt(intentList.size - 1),
                    PICKER_TITLE)
            chooserIntent!!
                    .putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toTypedArray<Parcelable>())
        }

        return chooserIntent
    }

    private fun addIntentsToList(context: Context, intentList: MutableList<Intent>, intent: Intent): MutableList<Intent> {
        val resolveInfoList = context.packageManager.queryIntentActivities(intent, 0)

        for (resolveInfo in resolveInfoList) {
            val packageName = resolveInfo.activityInfo.packageName

            val targetedIntent = Intent(intent)
            targetedIntent.setPackage(packageName)

            intentList.add(targetedIntent)
        }

        return intentList
    }

    /**
     * TODO: Write comment here
     * */
    fun getPickedImageInfo(context: Context, resultCode: Int, intentWithImage: Intent?): ImageInfo? {
        if (resultCode != Activity.RESULT_OK) {
            return null
        }

        val imageFile = getTempFileForImage(context) ?: return null

        val isCamera = (intentWithImage == null
                || intentWithImage.data == null
                || intentWithImage.data!!.toString().contains(imageFile.toString()))

        val selectedImageUri =
                if (isCamera) { // Camera
                    Uri.fromFile(imageFile)
                } else { // Album
                    intentWithImage!!.data
                }

        return ImageInfo(selectedImageUri, isCamera)
    }

    /**
     * TODO: Write comment here
     * */
    fun getPickedImageFromResult(context: Context, resultCode: Int, intentWithImage: Intent?): Bitmap? {
        if (resultCode != Activity.RESULT_OK) {
            return null
        }

        val pickedImageInfo =
                getPickedImageInfo(context, resultCode, intentWithImage) ?: return null

        var bitmap = getImageResized(context, pickedImageInfo.imageUri) ?: return null

        val rotation = getRotation(context,
                pickedImageInfo.imageUri, pickedImageInfo.isTakenByCamera)
        bitmap = rotate(bitmap, rotation)!!

        return bitmap
    }

    /*
     * Resize to avoid using too much memory loading big images (e.g.: 2560*1920)
     **/
    private fun getImageResized(context: Context, imageUri: Uri): Bitmap? {
        var bitmap: Bitmap?
        val sampleSizes = intArrayOf(5, 3, 2, 1)
        var i = 0
        do {
            bitmap = decodeBitmap(context, imageUri, sampleSizes[i])
            i++
        } while (bitmap!!.width < DEFAULT_MIN_WIDTH_QUALITY && i < sampleSizes.size)

        return bitmap
    }

    private fun decodeBitmap(context: Context, imageUri: Uri, sampleSize: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inSampleSize = sampleSize

        var fileDescriptor: AssetFileDescriptor? = null
        try {
            fileDescriptor = context.contentResolver.openAssetFileDescriptor(imageUri, "r")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        var actuallyUsableBitmap: Bitmap? = null

        if (fileDescriptor != null) {
            actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor.fileDescriptor,
                    null, options)
        }

        return actuallyUsableBitmap
    }

    private fun getRotation(context: Context, imageUri: Uri, isCamera: Boolean): Int {
        return if (isCamera) {
            getRotationFromCamera(context, imageUri)
        } else {
            getRotationFromGallery(context, imageUri)
        }
    }

    private fun getRotationFromCamera(context: Context, imageFile: Uri): Int {
        var rotate = 0
        try {
            context.contentResolver.notifyChange(imageFile, null)
            val exif = ExifInterface(imageFile.path)
            val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL)

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rotate
    }

    private fun getRotationFromGallery(context: Context, imageUri: Uri): Int {
        var result = 0
        val columns = arrayOf(MediaStore.Images.Media.ORIENTATION)
        var cursor: Cursor? = null
        try {
            cursor = context.contentResolver.query(imageUri, columns, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                val orientationColumnIndex = cursor.getColumnIndex(columns[0])
                result = cursor.getInt(orientationColumnIndex)
            }
        } catch (e: Exception) {
            // Do nothing
        } finally {
            cursor?.close()
        }

        return result
    }

    private fun rotate(bitmapIn: Bitmap, rotation: Int): Bitmap? {
        if (rotation != 0) {
            val matrix = Matrix()
            matrix.postRotate(rotation.toFloat())
            return Bitmap.createBitmap(bitmapIn, 0, 0,
                    bitmapIn.width, bitmapIn.height, matrix, true)
        }

        return bitmapIn
    }

    private fun getTempFileForImage(context: Context): File? {
        return getTempFile(context, TEMP_ORIGINAL_IMAGE, false)
    }

    private fun getEmptyTempFileForImage(context: Context): File? {
        return getTempFile(context, TEMP_ORIGINAL_IMAGE, true)
    }

    private fun makeTheTempFileEmpty(context: Context): Boolean {
        val file = getTempFileForImage(context) ?: return false
        return file.exists() && file.delete()
    }

    private fun getTempFile(context: Context, fileName: String, isEmptyFile: Boolean): File {
        val file = File(context.externalCacheDir, fileName)

        if (isEmptyFile) {
            file.delete()
        }

        if (file.parentFile == null) {
            file.mkdirs()
        } else {
            file.parentFile.mkdirs()
        }

        return file
    }
}
