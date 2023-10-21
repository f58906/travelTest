package com.example.cathaybktest

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.cathaybktest.databinding.FragmentContentBinding
import com.example.cathaybktest.model.Image
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class  ContentFragment : Fragment() {
    private lateinit var binding: FragmentContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get value
        val attractionName = arguments?.getString("attraction_name")
        val attractionIntroduction = arguments?.getString("attraction_introduction")
        val attractionImages: Array<Image>? = arguments?.getParcelableArray("attraction_images") as Array<Image>?
        val url = arguments?.getString("url")

        if (!attractionImages.isNullOrEmpty()) {
            val imageUrl = attractionImages.get(0).src
            Glide.with(binding.attractionImg)
                .load(imageUrl)
                .into(binding.attractionImg)

            //圖片點擊
            binding.attractionImg.setOnClickListener {
                val newParcelableImages = ArrayList<Parcelable>()
                for (image in attractionImages) {
                    newParcelableImages.add(image)
                }

                val intent = Intent(context, ImageDetailActivity::class.java)
                intent.putExtra("attraction_images", newParcelableImages)
                startActivity(intent)
            }
        }
        binding.attractionName.setText(attractionName)
        binding.attractionIntroduction.setText(attractionIntroduction)
        binding.title.setText(attractionName)
        binding.attractionName.setText(attractionName)
        binding.attractionUrl.setText(url)
        binding.lifecycleOwner = viewLifecycleOwner
        //返回監聽
        binding.back.setOnClickListener {
          requireActivity().supportFragmentManager.popBackStack()
        }
        binding.attractionUrl.setOnClickListener {
            url?.let { it1 -> setWebview(it1) }
        }



    }
  fun setWebview(url:String){
      val dialog = BottomSheetDialog(requireContext())
      // 設置布局
      val webViewView = LayoutInflater.from(context).inflate(R.layout.webview_layout, null)
      val webview = webViewView.findViewById<WebView>(R.id.webView)
      // 設置 WebView
      webview.settings.javaScriptEnabled = true
      webview.loadUrl(url)
      dialog.setContentView(webViewView)

      // 添加升起動畫
      val behavior = dialog.behavior
      if (behavior is BottomSheetBehavior) {
          behavior.isHideable = false
          behavior.state = BottomSheetBehavior.STATE_EXPANDED
      }

      // 顯示 BottomSheetDialog
      dialog.show()
  }


}