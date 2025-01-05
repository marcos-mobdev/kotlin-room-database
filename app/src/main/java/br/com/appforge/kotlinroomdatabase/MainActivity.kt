package br.com.appforge.kotlinroomdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.kotlinroomdatabase.data.UsersDatabase
import br.com.appforge.kotlinroomdatabase.data.dao.UserDAO
import br.com.appforge.kotlinroomdatabase.data.model.Address
import br.com.appforge.kotlinroomdatabase.data.model.User
import br.com.appforge.kotlinroomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var usersDatabase: UsersDatabase
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        usersDatabase = UsersDatabase.getInstance(this)
        userDAO = usersDatabase.getUserDao()


        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val user = User(0,"m@gmail.com", name, "123456", 12, 20.4, Address("Street A", 50), Date())
            CoroutineScope(Dispatchers.IO).launch {
                userDAO.save(user)
            }
        }
        binding.btnRemove.setOnClickListener {
            val user = User(2,"m@gmail.com", "Joao", "123456", 12, 20.4, Address("Street A", 50), Date())
            CoroutineScope(Dispatchers.IO).launch {
                userDAO.delete(user)
            }
        }
        binding.btnUpdate.setOnClickListener {
            val name = binding.editName.text.toString()
            val user = User(1,"m@gmail.com", name, "123456", 12, 20.4, Address("Street B", 100), Date())
            CoroutineScope(Dispatchers.IO).launch {
                userDAO.update(user)
            }
        }
        binding.btnList.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val userList = userDAO.list()
                var textUsers = ""
                userList.forEach { user->
                    val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm")
                    val formattedDate = formatter.format(user.dateOfSubscription)
                    textUsers += "${user.userId}) ${user.name} - ${user.age} - ${formattedDate}\n"
                }
                withContext(Dispatchers.Main){
                    binding.textUserList.text = textUsers
                }
            }
        }
        binding.btnSearch.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val searchText = binding.editName.text.toString()
                val userList = userDAO.search(searchText)
                var textUsers = ""
                userList.forEach { user ->
                    val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm")
                    val formattedDate = formatter.format(user.dateOfSubscription)
                    textUsers += "${user.userId}) ${user.name} - ${user.age} - ${formattedDate}\n"
                }
                withContext(Dispatchers.Main){
                    binding.textUserList.text = textUsers
                }
            }
        }
    }
}