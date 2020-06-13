
async function loadCategory() {
  try
  {
    var result = 
            await $.ajax({
              type: "GET", 
              url: `${ctx}/restaurant/category/us`, 
              contentType: "application/json"});
            
    console.log(result);
    return result;

  } catch (exception)
  {
    console.error(exception);
  }
}

async function loadDishes() {
  try
  {
    var result = 
            await $.ajax({
              type: "GET", 
              url: `${ctx}/restaurant/Dishes/dishes`, 
              contentType: "application/json"});
            
    console.log(result);
    console.log("la wea");
    return result;

  } catch (exception)
  {
    console.error(exception);
  }
}

async function verifyLogin(email,password)
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
    console.log(result);
    return result;
  
}

async function register(email, password, firstName, lastName, cellphone)
{
  var new_user = {
    email: email,
    password: password,
    firstName: firstName,
    lastName: lastName,
    cellphone: cellphone,
  };
  console.log(new_user);
  
    var result = 
            await $.ajax({
              type: "PUT",
              data: JSON.stringify(new_user),
              url: `${ctx}/restaurant/users/register`,
              contentType: "application/json"});
            
    console.log(result);
    return result;
}

async function loadAddiotionals(dish_id)
{
  try
  {
    var result = await $.ajax({
      type: "GET", 
      url:"restaurant/additionals?id="+dish_id,
      contentType: "application/json"});
    
    console.log(result);
    console.log("la wea");
    return result;

  } catch (exception)
  {
    console.error(exception);
  }
}
 

