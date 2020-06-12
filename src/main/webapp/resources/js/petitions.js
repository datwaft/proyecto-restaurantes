
async function loadCategory() {
  try
  {
    var result = await $.ajax({type: "GET", url: `${ctx}/restaurant/category/us`, contentType: "application/json"});
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
    var result = await $.ajax({type: "GET", url: `${ctx}/restaurant/Dishes/dishes`, contentType: "application/json"});
    console.log(result);
    console.log("la wea");
    return result;

  } catch (exception)
  {
    console.error(exception);
  }
}
//
//async function loadAddiotionals()
//{
//  id = "{nombre: $(namae del campo).val()};";
//  try
//  {
//    var result = await $.ajax({type: "GET", url:"restaurant/additionals?id="+id,contentType: "application/json"})
//    console.log(result);
//    console.log("la wea");
//    return result;
//
//  } catch (exception)
//  {
//    console.error(exception);
//  }
//}
 

