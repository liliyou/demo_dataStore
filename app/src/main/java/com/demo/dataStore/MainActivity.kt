package com.demo.dataStore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.dataStore.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnTitleString.setOnClickListener {
            binding.txtTitleString.text = binding.editTitleString.text.toString()
            coroutineScope.launch {
                mApp.preferencesGroup.testString.set(
                    binding.editTitleString.text.toString()
                )
            }

        }

        binding.switchBool.setOnCheckedChangeListener { _, _ ->
            binding.txtTitleBool.text = binding.switchBool.isChecked.toString()
            coroutineScope.launch {
                mApp.preferencesGroup.testBool.set(
                    binding.switchBool.isChecked
                )
            }
        }

        binding.btnTitleNumber.setOnClickListener {
            binding.txtTitleNumber.text = binding.editTitleNumber.text.toString()
            coroutineScope.launch {
                mApp.preferencesGroup.testLong.set(
                    binding.editTitleNumber.text.toString().toLong()
                )
            }

        }

        binding.btnTitleJson.setOnClickListener {
            binding.txtTitleJson.text = binding.editTitleJson.text.toString()
            coroutineScope.launch {
                mApp.preferencesGroup.defaultClass.set(
                    ResponeData(binding.editTitleJson.text.toString())
                )
            }
        }

        coroutineScope.launch {
            var str = mApp.preferencesGroup.testString.get()
            var bool = mApp.preferencesGroup.testBool.get()
            var long = mApp.preferencesGroup.testLong.get()
            var c = mApp.preferencesGroup.defaultClass.get()
            withContext(Dispatchers.Main) {
                binding.editTitleString.setText(str)
                binding.txtTitleString.text = str

                binding.switchBool.isChecked = bool
                binding.txtTitleBool.text = bool.toString()

                binding.editTitleNumber.setText(long.toString())
                binding.txtTitleNumber.text = long.toString()

                binding.editTitleJson.setText(c?.s)
                binding.txtTitleJson.text = c?.s
            }
        }
    }
}