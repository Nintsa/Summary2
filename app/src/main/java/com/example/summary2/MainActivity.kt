package com.example.summary2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.summary2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var anagramList = mutableListOf<String>()
    private lateinit var currString: String
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            currString = binding.enterAnagram.text.toString().trim()
            if (currString.isNotBlank()) {
                anagramList.add(currString)
                binding.enterAnagram.text?.clear()
            }
        }

        binding.outputBtn.setOnClickListener{
            count = groupAnagrams(anagramList)
            binding.anagramsCount.text = "$count"
        }
    }

    fun groupAnagrams(anagramList: List<String>): Int{
        val diffAnagrams = mutableListOf<List<String>>()
        val groupCount = BooleanArray(anagramList.size)

        for(i in anagramList.indices){
            if(!groupCount[i]){
                val currGroup = mutableListOf(anagramList[i])
                groupCount[i] = true

                for(j in anagramList.indices){
                    if(!groupCount[j] && diffAnagrams[i]==diffAnagrams[j]){
                        currGroup.add(anagramList[j])
                        groupCount[j] = true
                    }
                }

                diffAnagrams.add(currGroup)
            }
        }
        return diffAnagrams.size
    }
}