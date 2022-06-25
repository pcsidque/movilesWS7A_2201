import 'package:flutter/material.dart';
import 'package:app_my_drift_persistencia/database/database.dart';
import 'package:provider/provider.dart';
import 'package:app_my_drift_persistencia/ui/newUser.dart';

class listUser extends StatefulWidget {
  const listUser({Key? key}) : super(key: key);

  @override
  State<listUser> createState() => _listUserState();
}

class _listUserState extends State<listUser> {
  late AppDatabase database;

  @override
  Widget build(BuildContext context) {
    database = Provider.of<AppDatabase>(context); //creo un objeto del Provider

    return Scaffold(
      appBar: AppBar(//1
        title: Text("Users list"),
      ),

      body: FutureBuilder<List<User>>(//2
        future: database.getListUsers(),
        builder: (context, snapshot){
          if (snapshot.hasData){//hay datos?
            List<User>? userList = snapshot.data;
            return ListView.builder(
                itemCount: userList!.length,
                itemBuilder: (context, index){
                  User userData = userList[index];
                  return ListTile(
                      title: Text(userData.nombre),
                      subtitle: Text(userData.correo),
                      trailing: Icon(Icons.delete),
                      onTap:(){
                        setState(() {
                          database.deleteUser(UsersCompanion());
                        });
                      }
                  );
                });
          }
          else if (snapshot.hasError){//hay error?
            return Center(
              child: Text(snapshot.error.toString()),
            );
          }
          else{//para q no haya error
            return Center(
              child: Text(""),
            );
          }
        },
      ),

      floatingActionButton: FloatingActionButton(//3
        onPressed: (){
          addUser();
        },
        backgroundColor: Colors.red,
        child: Icon(
          Icons.add,
          color: Colors.white,
        ),
      ),
    );
  }

  void addUser() async{
    var res = await Navigator.push(context, //push para enviar
      MaterialPageRoute(
          builder: (context) => newUser()
      ),
    );
    if (res != null && res == true){
      setState(() {

      });
    }
  }
}
