package br.com.appforge.kotlinroomdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.kotlinroomdatabase.data.UsersDatabase
import br.com.appforge.kotlinroomdatabase.data.dao.AddressDAO
import br.com.appforge.kotlinroomdatabase.data.dao.CustomerOrderDAO
import br.com.appforge.kotlinroomdatabase.data.dao.PersonComputerDAO
import br.com.appforge.kotlinroomdatabase.data.dao.ProductDAO
import br.com.appforge.kotlinroomdatabase.data.dao.UserDAO
import br.com.appforge.kotlinroomdatabase.data.entity.Computer
import br.com.appforge.kotlinroomdatabase.data.entity.Customer
import br.com.appforge.kotlinroomdatabase.data.entity.Order
import br.com.appforge.kotlinroomdatabase.data.entity.Person
import br.com.appforge.kotlinroomdatabase.data.entity.PersonComputer
import br.com.appforge.kotlinroomdatabase.data.entity.User
import br.com.appforge.kotlinroomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var usersDatabase: UsersDatabase
    private lateinit var userDAO: UserDAO
    private lateinit var addressDAO: AddressDAO
    private lateinit var productDAO: ProductDAO
    private lateinit var customerOrderDAO: CustomerOrderDAO
    private lateinit var personComputerDAO: PersonComputerDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        usersDatabase = UsersDatabase.getInstance(this)
        userDAO = usersDatabase.userDAO
        addressDAO = usersDatabase.addressDAO
        productDAO = usersDatabase.productDAO
        customerOrderDAO = usersDatabase.customerOrderDAO
        personComputerDAO = usersDatabase.personComputerDAO


        binding.btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val personId = personComputerDAO.savePerson(
                    Person(0, "Maria", "F")
                )

                val computer1Id = personComputerDAO.saveComputer(
                    Computer(0, "XPS 15", "Dell")
                )
                val computer2Id = personComputerDAO.saveComputer(
                    Computer(0, "Macbook Pro M1", "Apple")
                )

                val personComputer1 = PersonComputer(personId, computer1Id)
                val personComputer2 = PersonComputer(personId, computer2Id)
                personComputerDAO.saveComputerPerson(personComputer1)
                personComputerDAO.saveComputerPerson(personComputer2)


            }


            /*
            val name = binding.editName.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val idCustomerInserted = customerOrderDAO.saveCustomer(
                    Customer(0,name, "Pinheiro")
                )
                repeat(3){ number ->
                    val order = Order(0,idCustomerInserted,"Product($number)", 2000.0+(50*number))
                    customerOrderDAO.saveOrder(order)
                }
            }

             */
            /*
            val name = binding.editName.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
               val idProductInserted = productDAO.saveProduct(
                   Product(0,name, 4000.99)
               )
                productDAO.saveProductDetails(ProductDetails(0,idProductInserted,"Apple", "Ultra Macbook 15"))
            }
             */
            /*
            val name = binding.editName.text.toString()
            val user = User(0,"m@gmail.com", name, "123456", 12, 20.4,
                //Address("Street A", 50),
                Date()
            )
            val address = Address(0,"Street Colorado, 20")
            CoroutineScope(Dispatchers.IO).launch {
                userDAO.save(user)
                addressDAO.save(address)
            }

             */
        }
        binding.btnRemove.setOnClickListener {
            val user = User(
                2, "m@gmail.com", "Joao", "123456", 12, 20.4,
                //Address("Street A", 50),
                Date()
            )
            CoroutineScope(Dispatchers.IO).launch {
                userDAO.delete(user)
            }
        }
        binding.btnUpdate.setOnClickListener {
            val name = binding.editName.text.toString()
            val user = User(
                1, "m@gmail.com", name, "123456", 12, 20.4,
                //Address("Street B", 100),
                Date()
            )
            CoroutineScope(Dispatchers.IO).launch {
                userDAO.update(user)
            }
        }
        binding.btnList.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val listPersonWithComputers = personComputerDAO.listPersonWithComputers()
                var listText = ""
                listPersonWithComputers.forEach { personWithComputers ->
                    val personId = personWithComputers.person.personId
                    val name = personWithComputers.person.name
                    listText += "$personId) $name has the following:\n"

                    personWithComputers.computers.forEach { computer->
                        val computerId = computer.computerId
                        val brand = computer.brand
                        val model = computer.model
                        listText += "\t$computerId) $brand - $model\n"
                    }
                    withContext(Dispatchers.Main){
                        binding.textUserList.text = listText
                    }
                }
            }


            /*
            CoroutineScope(Dispatchers.IO).launch {
                val list = customerOrderDAO.getCustomersAndOrders()
                var listText = ""
                list.forEach { customerAndOrder ->
                    val customerId = customerAndOrder.customer.customerId
                    val name = customerAndOrder.customer.name
                    val surname = customerAndOrder.customer.surname

                    listText += "$customerId) $name $surname has bought:\n"
                    customerAndOrder.orders.forEach { order ->
                        val orderId = order.orderId
                        val product = order.product
                        val price = order.price
                        listText += "\t$orderId) $product for R$ $price\n"
                    }



                    withContext(Dispatchers.Main) {
                        binding.textUserList.text = listText
                    }
                }
            }

             */
            /*
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

             */
            /*
            CoroutineScope(Dispatchers.IO).launch {
                val productList = productDAO.listProductsAndProductDetails()
                var textUsers = ""
                productList.forEach { productAndProductDetail->
                    val idProduct = productAndProductDetail.product.productId
                    val name = productAndProductDetail.product.name
                    val brand = productAndProductDetail.productDetails.brand
                    val description = productAndProductDetail.productDetails.description

                    textUsers += "$idProduct) $name - $brand - $description\n"
                }
                withContext(Dispatchers.Main){
                    binding.textUserList.text = textUsers
                }
            }

             */


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
                withContext(Dispatchers.Main) {
                    binding.textUserList.text = textUsers
                }
            }
        }
    }
}