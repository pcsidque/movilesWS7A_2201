import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
//usar un package
import 'package:english_words/english_words.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //final wordPair = WordPair.random();
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: RandomWord()
    );
  }
}

class RandomWord extends StatefulWidget {
  @override
  State<RandomWord> createState() => _RandomWordState();
}

class _RandomWordState extends State<RandomWord> {
  final List<WordPair> suggestions = <WordPair>[];
  final TextStyle biggerFont = TextStyle(fontSize: 16);
  final Set<WordPair> saved = Set<WordPair>();

  @override
  Widget build(BuildContext context) {
    //final wordPair = WordPair.random();
    //return Text(wordPair.asPascalCase);
    return Scaffold(
      appBar: AppBar(
        title: Text("** StartUp Name Generator **"),
        actions: <Widget>[
          IconButton(onPressed: pushSaved, icon: Icon(Icons.library_books))
        ],
      ),
      body: buildSuggestions(),
    );
  }

  Widget buildSuggestions(){
    return ListView.builder(
      padding: EdgeInsets.all(30),
        itemBuilder: (BuildContext context, int i){
          if (i.isOdd){
            return Divider(
              thickness: 2,
              color: Colors.limeAccent,
            );
          }

          final int index = i ~/ 2;
          if (index >= suggestions.length){
            suggestions.addAll(generateWordPairs().take(10));
          }
          return buildRow(suggestions[index]);
        }
        );
  }

  Widget buildRow(WordPair suggestion) {
    final bool alreadySaved = saved.contains(suggestion);

    return ListTile(
      title: Text(
        suggestion.asPascalCase,
        style: biggerFont,
      ),
      trailing: Icon(
          alreadySaved ? Icons.favorite : Icons.favorite_border,
          color: alreadySaved ? Colors.red : null
      ),
      onTap: (){
        setState(() {
          if (alreadySaved){
            saved.remove(suggestion);
          }
          else{
            saved.add(suggestion);
          }
        });
      },
    );
  }

  void pushSaved() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
          builder: (BuildContext context){
            final Iterable<ListTile> tiles = saved.map(
                    (WordPair pair){
                  return ListTile(
                    title: Text(
                      pair.asPascalCase,
                      style: biggerFont,
                    ),
                  );
                }
            );
            final List<Widget> divided = ListTile.divideTiles(
              context: context,
              tiles: tiles,
            ).toList();
            return Scaffold(
              appBar: AppBar(
                title: Text("Saved favorite Names"),
              ),
              body: ListView(
                  children: divided),
            );
          }),
    );
  }
}