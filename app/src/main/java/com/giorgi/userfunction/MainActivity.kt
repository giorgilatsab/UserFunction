package com.giorgi.userfunction

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.giorgi.userfunction.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val multi = mutableSetOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtaddUser.setOnClickListener{
            addUser()
        }
        binding.BtremoveUser.setOnClickListener{
            delete()
        }
    }
    fun addUser(){

        val firstName = binding.EdFirstname.text.toString()
        val lastNmae = binding.EdLastname.text.toString()
        val age = binding.EdAge.text.toString()
        val email = binding.EdEmail.text.toString()

        if (checkEdit(firstName) && checkEdit(lastNmae) && checkEdit(age) && checkEdit(email)) {
             when {
                 !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                     Toast.makeText(this, "ჩაწერეთ ვალიდური მეილი", Toast.LENGTH_SHORT).show()

                 age.toInt() <= 0 ->
                     Toast.makeText(this, "ჩაწერეთ ნატურალური რიცხვი", Toast.LENGTH_SHORT).show()

                 else ->  addlist()
             }
         }
        else{
             Toast.makeText(this, "შეავსეთ ყველა ველი", Toast.LENGTH_SHORT).show()
        }
    }
    fun addlist(){
         val firstName = binding.EdFirstname.text.toString()
         val lastNmae = binding.EdLastname.text.toString()
         val age = binding.EdAge.text.toString()
         val email = binding.EdEmail.text.toString()

        binding.TxSuccess.setTextColor(Color.GRAY)

         val user = User(firstName,lastNmae,age,email)

           if(multi.isEmpty()){
               Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()

               binding.TxSuccess.setTextColor(Color.GREEN)

               multi.add(user)
           }
           else {
               for (i in multi) {
                   if (i == user) {
                       Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                   } else {
                       Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
                       binding.TxSuccess.setTextColor(Color.GREEN)
                   }
               }
           }
     }


    fun delete(){

        val email = binding.EdEmail.text.toString()

        if (multi.isEmpty()){
            Toast.makeText(this, "User does not exits", Toast.LENGTH_SHORT).show()

        }

        for (i in multi){
            if(i.email == email){
                multi.remove(i)
                Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show()
                binding.TxSuccess.setTextColor(Color.GREEN)
            }
            else{
                Toast.makeText(this, "User does not exits", Toast.LENGTH_SHORT).show()
                binding.TxSuccess.setTextColor(Color.GRAY)
            }
        }

    }
    fun checkEdit(value: String):Boolean {
        return value.isNotEmpty()
    }
}
