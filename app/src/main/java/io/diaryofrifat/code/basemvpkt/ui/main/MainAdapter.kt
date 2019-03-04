package io.diaryofrifat.code.basemvpkt.ui.main

import android.text.TextUtils
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import io.diaryofrifat.code.basemvpkt.R
import io.diaryofrifat.code.basemvpkt.data.remote.retrophoto.RetroPhoto
import io.diaryofrifat.code.basemvpkt.databinding.ItemRetroPhotoBinding
import io.diaryofrifat.code.basemvpkt.ui.base.component.BaseAdapter
import io.diaryofrifat.code.basemvpkt.ui.base.component.BaseViewHolder
import io.diaryofrifat.code.utils.libs.GlideUtils

class MainAdapter : BaseAdapter<RetroPhoto>() {
    override fun isEqual(left: RetroPhoto, right: RetroPhoto): Boolean {
        return left.id == right.id
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RetroPhoto> {
        return PhotoViewHolder(inflate(parent, R.layout.item_retro_photo))
    }

    class PhotoViewHolder(binding: ViewDataBinding) : BaseViewHolder<RetroPhoto>(binding) {
        private val mBinding: ItemRetroPhotoBinding = binding as ItemRetroPhotoBinding

        override fun bind(item: RetroPhoto) {
            mBinding.textViewTitle.text = item.title
            if (!TextUtils.isEmpty(item.url)) {
                GlideUtils.normalWithCaching(mBinding.imageViewPhoto, item.url!!)
            }

            mBinding.executePendingBindings()
        }
    }
}