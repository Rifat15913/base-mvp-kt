package io.diaryofrifat.code.utils.helper.imagepicker

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import io.diaryofrifat.code.basemvp.R
import io.diaryofrifat.code.utils.helper.DataUtils
import io.diaryofrifat.code.utils.helper.FileUtils
import java.io.File
import java.util.*

// TODO: Test this class
object ImagePickerUtils {
    /**
     * Constants
     * */
    private const val PICKER_TITLE = "Pick Image"
    private const val REQUEST_CODE_PICK_IMAGE = 15913

    /**
     * Fields
     * */
    private var mListener: Listener? = null
    private var mCapturedImageFile: File? = null

    /**
     * This method provides option to pick image from camera, gallery and other applications
     *
     * @param activity current activity
     * @param listener callback to send the states back
     * */
    @Synchronized
    fun pickImage(activity: Activity, listener: Listener) {
        mListener = listener
        val imagePickingIntent = getImagePickingIntent(activity)

        if (imagePickingIntent != null) {
            activity.startActivityForResult(imagePickingIntent, REQUEST_CODE_PICK_IMAGE)
        } else {
            mListener?.onError(NullPointerException(
                    activity.getString(R.string.error_image_picking_intent_is_null)))
        }
    }

    /**
     * This method provides option to pick image from camera, gallery and other applications
     *
     * @param fragment current fragment
     * @param listener callback to send the states back
     * */
    @Synchronized
    fun pickImage(fragment: Fragment, listener: Listener) {
        mListener = listener

        if (fragment.context != null) {
            val imagePickingIntent = getImagePickingIntent(fragment.context!!)

            if (imagePickingIntent != null) {
                fragment.startActivityForResult(imagePickingIntent, REQUEST_CODE_PICK_IMAGE)
            } else {
                mListener?.onError(NullPointerException(
                        fragment.getString(R.string.error_image_picking_intent_is_null)))
            }
        } else {
            mListener?.onError(NullPointerException(
                    fragment.getString(R.string.error_fragment_context_is_null)))
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, intentWithImage: Intent?) {
        if (requestCode != REQUEST_CODE_PICK_IMAGE || resultCode != RESULT_OK) {
            mCapturedImageFile?.delete()
            clearUtil()
            return
        }

        val isCamera = intentWithImage == null || intentWithImage.data == null
                || (intentWithImage.action != null
                && intentWithImage.action == MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            val selectedImageUri =
                    if (isCamera) Uri.fromFile(mCapturedImageFile) else intentWithImage!!.data

            if (selectedImageUri != null) {
                mListener?.onSuccess(ImageInfo(selectedImageUri, isCamera))
            } else {
                mListener?.onError(NullPointerException(
                        DataUtils.getString(R.string.error_selected_image_uri_is_null)))
            }
        } catch (error: Exception) {
            mListener?.onError(error)
        }

        if (!isCamera) mCapturedImageFile?.delete()
        clearUtil()
    }

    private fun getImagePickingIntent(context: Context): Intent? {

        // TODO: Find bug by entering here without taking permission from user

        var chooserIntent: Intent? = null
        var intentList: MutableList<Intent> = ArrayList()

        val pickPhotoIntent = Intent()
        pickPhotoIntent.type = context.getString(R.string.intent_type_image)
        pickPhotoIntent.action = Intent.ACTION_GET_CONTENT

        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePhotoIntent.putExtra(context.getString(R.string.intent_extra_return_data), true)

        val emptyFile = FileUtils.getEmptyFileForSavingCapturedImage(context)
        if (emptyFile == null) {
            return null
        } else {
            mCapturedImageFile = emptyFile
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(context,
                    context.getString(R.string.file_provider_authority), mCapturedImageFile!!))
            takePhotoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            takePhotoIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else {
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(emptyFile))
        }

        intentList = addIntentsToList(context, intentList, pickPhotoIntent)
        intentList = addIntentsToList(context, intentList, takePhotoIntent)

        if (intentList.size > 0) {
            chooserIntent = Intent.createChooser(intentList.removeAt(intentList.size - 1), PICKER_TITLE)
            chooserIntent!!.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toTypedArray<Parcelable>())
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

    private fun clearUtil() {
        mListener = null
        mCapturedImageFile = null
    }

    interface Listener {
        fun onSuccess(imageInfo: ImageInfo)
        fun onError(error: Throwable)
    }
}
