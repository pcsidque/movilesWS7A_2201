import 'package:flutter/material.dart';
import 'package:app_my_drift_persistencia/ui/listUser.dart';
import 'package:app_my_drift_persistencia/database/database.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Provider(
      create: (context) => AppDatabase(), //con el provider creo la BD
      child: MaterialApp(
        theme: ThemeData(
          primarySwatch: Colors.deepPurple,
        ),
        home: listUser(),
      ),
    );
  }
}

