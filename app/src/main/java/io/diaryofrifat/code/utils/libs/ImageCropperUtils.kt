package io.diaryofrifat.code.utils.libs

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.support.v4.content.ContextCompat
import com.yalantis.ucrop.UCrop
import io.diaryofrifat.code.basemvp.R
import java.io.File

// TODO: Test this class
object ImageCropperUtils {

    // Constants
    private const val TEMP_CROPPED_IMAGE = "tempCroppedImage.jpg"
    private const val DEFAULT_MAX_WIDTH = 200
    private const val DEFAULT_MAX_HEIGHT = 200

    /**
     * This method crops image in square shape using default max width and height
     *
     * @param activity current activity
     * @param sourceUri the image to be cropped
     * @param destinationUri the cropped image
     * */
    fun cropImage(activity: Activity, sourceUri: Uri, destinationUri: Uri) {
        cropImage(activity, sourceUri, destinationUri, CropRatio.DEFAULT,
                DEFAULT_MAX_WIDTH, DEFAULT_MAX_HEIGHT)
    }

    /**
     * This method crops image in custom shape using default max width and height
     *
     * @param activity current activity
     * @param sourceUri the image to be cropped
     * @param destinationUri the cropped image
     * @param cropRatio crop ratio of the image
     * */
    fun cropImage(activity: Activity, sourceUri: Uri, destinationUri: Uri, cropRatio: CropRatio) {
        cropImage(activity, sourceUri, destinationUri, cropRatio,
                DEFAULT_MAX_WIDTH, DEFAULT_MAX_HEIGHT)
    }

    /**
     * This method crops image with custom params
     *
     * @param activity current activity
     * @param sourceUri the image to be cropped
     * @param destinationUri the cropped image
     * @param cropRatio crop ratio of the image
     * @param maxWidthResult max width size for image
     * @param maxHeightResult max height size for image
     * */
    fun cropImage(activity: Activity,
                  sourceUri: Uri, destinationUri: Uri,
                  cropRatio: CropRatio,
                  maxWidthResult: Int, maxHeightResult: Int) {

        val options = UCrop.Options()
        options.setActiveWidgetColor(ContextCompat.getColor(activity, R.color.colorPrimary))
        options.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimary))
        options.setStatusBarColor(ContextCompat.getColor(activity, R.color.colorPrimary))

        val cropper = UCrop.of(sourceUri, destinationUri)
                .withOptions(options)
                .withMaxResultSize(maxWidthResult, maxHeightResult)

        when (cropRatio) {
            CropRatio.DEFAULT -> {
                cropper.withAspectRatio(1.toFloat(), 1.toFloat())
            }

            CropRatio.SQUARE -> {
                cropper.withAspectRatio(1.toFloat(), 1.toFloat())
            }

            CropRatio.WIDE -> {
                cropper.withAspectRatio(16.toFloat(), 9.toFloat())
            }

            CropRatio.EXTRA_WIDE -> {
                cropper.withAspectRatio(18.toFloat(), 9.toFloat())
            }

            CropRatio.REGULAR -> {
                cropper.withAspectRatio(4.toFloat(), 3.toFloat())
            }

            else -> {
                // Skip
            }
        }

        cropper.start(activity)
    }

    private fun getTempFileForImage(context: Context): File? {
        return getTempFile(context, TEMP_CROPPED_IMAGE, false)
    }

    /**
     * This method provides the uri of the cropped image
     *
     * @param context current UI context
     * */
    fun getCroppedImageDestinationUri(context: Context): Uri? {
        val imageFile = getTempFileForImage(context)

        return if (imageFile != null) {
            Uri.fromFile(imageFile)
        } else {
            null
        }
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

    enum class CropRatio {
        DEFAULT, // 1:1
        SQUARE, // 1:1
        WIDE, // 16:9
        EXTRA_WIDE, // 18:9
        REGULAR, // 4:3
        CUSTOM
    }
}
