import 'package:drift/drift.dart';
import 'package:drift/native.dart';
import 'package:path/path.dart' as p;
import 'package:path_provider/path_provider.dart';
import 'dart:io';
part 'database.g.dart';

class Users extends Table{
  IntColumn get id => integer().autoIncrement()();
  TextColumn get nombre => text().named("nombre")();
  TextColumn get correo => text()();
}

LazyDatabase openConnection(){
  return LazyDatabase(() async{
    final dbfolder = await getApplicationDocumentsDirectory();
    final file = File(p.join(dbfolder.path,'prudb.sqlite'));
    return NativeDatabase(file);
  });
}

@DriftDatabase(tables: [Users])
class AppDatabase extends _$AppDatabase {
  AppDatabase(): super(openConnection());
  @override
  int get schemaVersion => 1;

  //metodos C R U D
  //listar
  Future<List<User>> getListUsers() async{
    return await select(users).get();
  }

  //nuevo
  Future<int> insertUser(UsersCompanion usersCompanion) async{
    return await into(users).insert(usersCompanion);
  }

  //eliminar
  Future<int> deleteUser(UsersCompanion usersCompanion) async{
    return await delete(users).delete(usersCompanion);
  }

  //actualizar
  Future<bool> updateUser(UsersCompanion usersCompanion) async{
    return await update(users).replace(usersCompanion);
  }
}