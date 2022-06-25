import 'package:flutter/material.dart';
import 'package:app_my_shopping_list/utils/dbhelper.dart';
import 'package:app_my_shopping_list/models/shopping_list.dart';

class ShoppingListDialog{
  final txtName = TextEditingController();
  final txtPriority = TextEditingController();

  //creamos el metodo "buildDialog", que recibira 3 parametros:
  //1. el contexto
  //2. la tabla
  //3. un booleano, que determinara:
    //--> si es FALSO = edit
    //--> si es VERD = new

  Widget buildDialog(BuildContext context, ShoppingList list, bool isNew){
    DbHelper helper = DbHelper();
    if(!isNew){
      txtName.text = list.name;
      txtPriority.text = list.priority.toString();
    }
    else{
      txtName.text = "";
      txtPriority.text = "";
    }

    return AlertDialog(
      title: Text((isNew)? 'New shopping list' : 'Edit shopping list'),
      content: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            TextField(
              controller: txtName,
              decoration: InputDecoration(
                  hintText: 'Name'
              ),
            ),
            TextField(
              controller: txtPriority,
              decoration: InputDecoration(
                  hintText: 'Priority (1-3)'
              ),
              keyboardType: TextInputType.number,
            ),
            ElevatedButton(
              child: Text("Save"),
              onPressed: (){
                list.name = txtName.text;
                list.priority = int.parse(txtPriority.text);
                helper.insertList(list);
                Navigator.pop(context);
              },
            )
          ],
        ),
      ),
    );
  }
}