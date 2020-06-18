
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


async function loadAddiotionals()
{
  try
  {
    var result = await $.ajax({
      type: "GET", 
      url:"restaurant/Additionals/additionals",
      contentType: "application/json"});
    
    console.log(result);
    console.log("la wea");
    return result;

  } catch (exception)
  {
    console.error(exception);
  }
}

async function loadAddiotionalsCategory() 
{
  try
  {
    var result = await $.ajax({
      type: "GET", 
      url:"restaurant/Additionals/adi-cats",
      contentType: "application/json"});

      console.log(result);
    return result;

  } catch (exception)
  {
    console.error(exception);
  }
}

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

async function updateCategory(id,description)
{
  var category = {
    id:id,
    description: description,
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

async function updateCategory(id,description)
{
  var category = {
    id:id,
    description: description,
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

async function updateDish(id,category,price,name,description)
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

async function updateOrderStatus(id,status)
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

    var bill = {
      address1:id
    };
    
    var result = 
            await $.ajax({
              type: "GET", 
              data: JSON.stringify(bill), 
              url: `${ctx}/restaurant/Orders/order`,            
              contentType: "application/json"});
    console.log(result);
    return result;
  
}

//-----------------------------Terminan gets especificos----------------------------//

function prueba()
{
  var array = [1,2,3];
  var array1 = [3,4,5];
  var array2 = [6,7,8];
  
  var objeto1 =
          {
            lista1:array,
    lista2:array1,
    lista3:array2
  };
  
  return objeto1;

}

//-------------------------------------Creates--------------------------------------//


async function createAddress(user,address1,address2,city,postcode,country)
{
  var Address = {
    id:null,
    user: user,
    address1:address1,
    address2:address2,
    city:city,
    postcode:postcode,
    country:country
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