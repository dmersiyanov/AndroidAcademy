package com.dmity.androidacademy.features.onboarding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dmity.androidacademy.R
import kotlinx.android.synthetic.main.fragment_onboarding.*

private const val ARG_IMAGE_ID = "image_id"
private const val ARG_TITLE_ID = "title_id"

class OnBoardingFragment : Fragment() {

    private var imageId: Int? = null
    private var titleId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageId = it.getInt(ARG_IMAGE_ID)
            titleId = it.getInt(ARG_TITLE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageId?.let {
            ivImage.setImageDrawable(ContextCompat.getDrawable(context!!, it))
        }
        titleId?.let {
            tvTitle.text = getString(it)
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(imageId: Int, titleId: Int) =
                OnBoardingFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_IMAGE_ID, imageId)
                        putInt(ARG_TITLE_ID, titleId)
                    }
                }
    }
}
