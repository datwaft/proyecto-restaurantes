
//-------------------------------Inician los llamados para carga----------------------------//
async function loadCategory() {  //Funcionando
  try
  {
    var result = 
            await $.ajax({
              type: "GET", 
              url: `${ctx}/restaurant/category/us`, 
              contentType: "application/json"});
    return result;

  } catch (exception)
  {
    console.error(exception);
  }
}

async function loadDishes() {  //Funcionando
  try
  {
    var result = 
            await $.ajax({
              type: "GET", 
              url: `${ctx}/restaurant/Dishes/dishes`, 
              contentType: "application/json"});

    return result;

  } catch (exception)
  {
    console.error(exception);
  }
}

async function loadUsers() // funcionando
{

    var result = 
            await $.ajax({
              type: "GET",
              url: `${ctx}/restaurant/users/users`,            
              contentType: "application/json"});
    return result;
}

async function loadAdmins() //funcionando
{
   var result = 
            await $.ajax({
              type: "GET",
              url: `${ctx}/restaurant/users/admins`,            
              contentType: "application/json"});
    return result;
}


//async function loadAddiotionals()
//{
//  try
//  {
//    var result = await $.ajax({
//      type: "GET", 
//      url:"restaurant/Additionals/additionals",
//      contentType: "application/json"});
//    
//    console.log(result);
//    return result;
//
//  } catch (exception)
//  {
//    console.error(exception);
//  }
//}

//async function loadAddiotionalsCategory() 
//{
//  try
//  {
//    var result = await $.ajax({
//      type: "GET", 
//      url:"restaurant/Additionals/adi-cats",
//      contentType: "application/json"});
//
//      console.log(result);
//    return result;
//
//  } catch (exception)
//  {
//    console.error(exception);
//  }
//}

//-------------------------------Terminan los llamados para carga----------------------------//

//-------------------------------Inician peticiones relacionadas con el login y register-------------//

async function verifyLogin(email,password)  //Funcionando
{
    console.log(email,password);
    var user = {
      email:email,
      password:password
    };
    
    var result = 
            await $.ajax({
              type: "POST", 
              data: JSON.stringify(user), 
              url: `${ctx}/restaurant/users/login`,            
              contentType: "application/json"});
    return result;
  
}

async function verifyAdminLogin(username,password) //Funcionando
{
    console.log(username,password);
    var user = {
      username:username,
      password:password
    };
    
    var result = 
            await $.ajax({
              type: "POST", 
              data: JSON.stringify(user), 
              url: `${ctx}/restaurant/users/login-admin`,            
              contentType: "application/json"});
    return result;
  
}

async function register(email, password, firstName, lastName, cellphone) //funcionando
{
  var new_user = {
    email: email,
    password: password,
    firstName: firstName,
    lastName: lastName,
    cellphone: cellphone,
  };
  
    var result = 
            await $.ajax({
              type: "POST",
              data: JSON.stringify(new_user),
              url: `${ctx}/restaurant/users/register`,
              contentType: "application/json"});
            
    return result;
}

async function registerAdmin(username, password) //funcionando
{
  var new_user = {
    username: username,
    password: password
  };
  
    var result = 
            await $.ajax({
              type: "POST",
              data: JSON.stringify(new_user),
              url: `${ctx}/restaurant/users/register-admin`,
              contentType: "application/json"});
            
    return result;
}
//-------------------------------Terminan peticiones relacionadas con el login y register-------------//

//---------------------------------INICIAN UPDATES---------------------------------//
async function updateUser(email, password, firstName, lastName, cellphone) //funcionando
{
  var new_user = {
    email: email,
    password: password,
    firstName: firstName,
    lastName: lastName,
    cellphone: cellphone
  };
    var result = 
            await $.ajax({
              type: "PUT",
              data: JSON.stringify(new_user),
              url: `${ctx}/restaurant/users/update`,
              contentType: "application/json"});

    return result;
}

async function updateAdmin(username,password) //funcionando
{
  var new_user = {
    username:username,
    password: password
  };
    var result = 
            await $.ajax({
              type: "PUT",
              data: JSON.stringify(new_user),
              url: `${ctx}/restaurant/users/update-admin`,
              contentType: "application/json"});

    return result;
}

async function updateCategory(id,description)  // Funcionando
{
  var category = {
    id:id,
    description: description
  };
  var result = 
            await $.ajax({
              type: "PUT",
              data: JSON.stringify(category),
              url: `${ctx}/restaurant/category/update`,
              contentType: "application/json"});
            
    console.log(result);
    return result; 
}

async function updateDish(id,category,price,name,description)   //Funcionando, necesita si o si la categoria completa
{
  var dish = {
    id:id,
    category:category,
    price:price,
    name:name,
    description:description
  };
  var result = 
            await $.ajax({
              type: "PUT",
              data: JSON.stringify(dish),
              url: `${ctx}/restaurant/Dishes/update`,
              contentType: "application/json"});          
    console.log(result);
    return result;  
}

async function updateOrderStatus(id,status)   //Funcionando
{
   var new_status = {
    id:id,
    status:status
  };
  
  var result = 
            await $.ajax({
              type: "PUT",
              data: JSON.stringify(new_status),
              url: `${ctx}/restaurant/Orders/status`,
              contentType: "application/json"});
            
    console.log(result);
    return result;  
}

async function updateAddress(id,address1,address2,city,postcode,country,state)  //Funcionando
{
   var Address = {
    id:id,
    user: null,
    address1:address1,
    address2:address2,
    city:city,
    postcode:postcode,
    country:country,
    state:state
    
  };
  console.log(Address);
  
  var result = 
            await $.ajax({
              type: "PUT",
              data: JSON.stringify(Address),
              url: `${ctx}/restaurant/Address/update`,
              contentType: "application/json"});
            
    console.log(result);
    return result;  
}

async function updateAdditional(id,price,description,additionalCategory) //Funcionando 
{
  var additional = {
    id:id,
    price:price,
    description:description,
    additionalCategory:additionalCategory
  };
  
   var result = 
            await $.ajax({
              type: "PUT",
              data: JSON.stringify(additional),
              url: `${ctx}/restaurant/Additionals/update`,
              contentType: "application/json"});
            
    console.log(result);
    return result;  
  
}

//---------------------------------TERMINAN UPDATES---------------------------------//

//-----------------------------Inician gets especificos-----------------------------//

async function getDirections(id)  //Funcionando.
{
    var result = 
            await $.ajax({
              type: "GET", 
              url: `${ctx}/restaurant/Address/address/`+id,            
              contentType: "application/json"});
    return result;
  
}

async function getBills(id)
{
  var result =
          await $.ajax({
            type: "GET",
            url: `${ctx}/restaurant/Orders/orders/` + id,
            contentType: "application/json"});
  if (result.length > 20)
    return result.slice(-20);
  else {
    return result;
  }
}


async function getAllBills()
{
    var result = 
            await $.ajax({
              type: "GET", 
              url: `${ctx}/restaurant/Orders/orders/all`,            
              contentType: "application/json"});
    console.log(result);
    return result;
  
}

async function getSelecteds(id)  //Funcionando
{
   var result = 
            await $.ajax({
              type: "GET", 
              url: `${ctx}/restaurant/Selected/getall/`+id,            
              contentType: "application/json"});
    return result;

}

async function getDishComplements(id) // Funcionando
{
  var result = 
            await $.ajax({
              type: "GET", 
              url: `${ctx}/restaurant/Dishes/complements/`+id,            
              contentType: "application/json"});
    return result;
}



//-----------------------------Terminan gets especificos----------------------------//

async function prueba()
{
//   var result = 
//            await $.ajax({
//              type: "GET", 
//              url: `${ctx}/restaurant/Dishes/complements/`+3,            
//              contentType: "application/json"});
//    return result;

}

//-------------------------------------Creates--------------------------------------//


async function createAddress(user,address1,address2,city,postcode,country,state)  //Funcionando
{
  var Address = {
    id:null,
    user: user,
    address1:address1,
    address2:address2,
    city:city,
    postcode:postcode,
    country:country,
    state:state
  };
  console.log(Address);
  
  var result = 
            await $.ajax({
              type: "POST",
              data: JSON.stringify(Address),
              url: `${ctx}/restaurant/Address/create`,
              contentType: "application/json"});
            
    console.log(result);
    return result;  
  
}


async function createDish(id,category,price,name,description)  //Funcionando
{
  var dish = {
    id:id,
    category:category,
    price:price,
    name:name,
    description:description
  };
  var result = 
            await $.ajax({
              type: "POST",
              data: JSON.stringify(dish),
              url: `${ctx}/restaurant/Dishes/create`,
              contentType: "application/json"});          
    console.log(result);
    return result;  
}

async function createCategory(description)  //Funcionando
{
  var desc = {
    description:description
  };
  var result = 
            await $.ajax({
              type: "POST",
              data: JSON.stringify(desc),
              url: `${ctx}/restaurant/category/create`,
              contentType: "application/json"});          
    console.log(result);
    return result;  
}

async function createAdditional(price, description, additionalCategory) //Funcionando
{
  var additional ={
    price:price,
    description:description,
    additionalcategory:additionalCategory
  };
          
  var result = 
            await $.ajax({
              type: "POST",
              data: JSON.stringify(additional),
              url: `${ctx}/restaurant/Additionals/create`,
              contentType: "application/json"});          
    console.log(result);
    return result;  
}

async function createAdditionalCategory(dish,description,multiple,required) //Funcionando, multiple y required deben ser parametros booleanos, 1 y 0 no funciona....
{
  var additionalcat ={
    dish:dish,
    description:description,
    multiple:multiple,
    required:required
  };
          
  var result = 
            await $.ajax({
              type: "POST",
              data: JSON.stringify(additionalcat),
              url: `${ctx}/restaurant/Additionals/create-cat`,
              contentType: "application/json"});          
    console.log(result);
    return result;  
}

async function createBill(user,address,name,orderType,orderTime,status)  //Funcionando, se necesita string en formato yyyy-MM-dd HH:mm:ss
{
  var today = new Date();
  var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate()+' '+today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();

  var orders ={
    user:user,
    address:address,
    name:name,
    orderType:orderType,
    orderTime:orderTime,
    status:status
  };

  var result = 
            await $.ajax({
              type: "POST",
              data: JSON.stringify(orders),
              url: `${ctx}/restaurant/Orders/create/`+date,
              contentType: "application/json"});          
    console.log(result);
    return result;  
  
}

//--------------------------------------Terminan creates -----------------------//

//-------------------------------------Inician deletes--------------------------//

async function deleteDirections(id)  //Funcionando
{
    var result = 
            await $.ajax({
              type: "DELETE", 
              url: `${ctx}/restaurant/Address/delete/`+id,            
              contentType: "application/json"});
    return result;
  
}



