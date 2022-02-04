package com.example.stringemojifier

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stringemojifier.databinding.FragmentEmojiBinding
import com.example.stringemojifier.network.User
import com.example.stringemojifier.network.UserResponse

lateinit var binding: FragmentEmojiBinding
lateinit var viewModel: ConvertToEmojiViewModel
class ConvertToEmoji : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmojiBinding.inflate(layoutInflater)

        initViewModel()
        binding.doneButton.setOnClickListener {
            convertString()
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ConvertToEmojiViewModel::class.java]
        viewModel.getResult().observe(viewLifecycleOwner, Observer <UserResponse?>{

            if(it  == null) {
              println("no value provided")
            } else {
                binding.apply {
                    responseText.text = it.stringConvertedToEmoji
                    doneButton.visibility = View.GONE
                    valueText.visibility = View.GONE
                }

            }
        })
    }

    private fun convertString() {
        val user = User(binding.valueText.text.toString())
        viewModel.convertString(user)
    }

}