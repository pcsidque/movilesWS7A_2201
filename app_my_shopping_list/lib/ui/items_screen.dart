import 'package:flutter/material.dart';
import 'package:app_my_shopping_list/utils/dbhelper.dart';
import 'package:app_my_shopping_list/models/shopping_list.dart';
import 'package:app_my_shopping_list/models/list_items.dart';
import 'package:app_my_shopping_list/ui/list_item_dialog.dart';


class ItemsScreen extends StatefulWidget {
  final ShoppingList shoppingList;
  ItemsScreen(this.shoppingList);

  @override
  State<ItemsScreen> createState() => _ItemsScreenState(this.shoppingList);
}

class _ItemsScreenState extends State<ItemsScreen> {
  final ShoppingList shoppingList;
  _ItemsScreenState(this.shoppingList);

  DbHelper? helper;
  List<ListItem> items=[];

  @override
  Widget build(BuildContext context) {
    helper= DbHelper();
    ListItemDialog dialog = ListItemDialog();
    showdata(this.shoppingList.id);

    return Scaffold(
      appBar: AppBar(
        title: Text(shoppingList.name),
      ),
      body: ListView.builder(
          itemCount: (items != null) ? items.length : 0,
          itemBuilder: (BuildContext context, int index) {
            return Dismissible(
              key: Key(items[index].name),
              onDismissed: (direction) {
                String strName = items[index].name;
                helper!.deleteItem(items[index]);
                setState(() {
                  items.removeAt(index);
                });
                Scaffold
                    .of(context)
                    .showSnackBar(SnackBar(content: Text("$strName deleted")));
              },
              child: ListTile(
                  title: Text(items[index].name),
                  subtitle:
                  Text(items[index].quantity + ' - ' + items[index].note),
                  trailing: IconButton(
                    icon: Icon(Icons.edit),
                    onPressed: () {
                      showDialog(
                        context: context,
                        builder: (BuildContext context) =>
                            dialog.buildDialog(context, items[index], false),
                      );
                    },
                  )),
            );
          }),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          showDialog(
            context: context,
            builder: (BuildContext context) => dialog.buildDialog(
                context, ListItem(0, shoppingList.id, '', '', ''), true),
          );
        },
        child: Icon(Icons.add),
        backgroundColor: Colors.pink,
      ),
    );
  }

  Future showdata(int idList) async {
    await helper!.openDb();
    items = await helper!.getItems(idList);
    setState(() {
      items = items;
    });
  }
}
