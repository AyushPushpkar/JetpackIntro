package com.example.jetpackintro

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jetpackintro.databinding.FragmentMigrationBinding


class MigrationFragment : Fragment() {

    private var _binding : FragmentMigrationBinding? = null
    private val binding : FragmentMigrationBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMigrationBinding.inflate(inflater,container,false)

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
//                Text(text = "Hello World")

                AndroidView(
                    factory = {
                        TextView(it)
                    }
                ){
                    it.apply {
                        text = "This is xml in a composable"
                        gravity = Gravity.CENTER
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}