
// implement OnHttpLoaded interface for HttpRequest component
onHttpLoaded(data) {
   Alert.toast("got data");
   activity.findViewById(id.main_text).setText(data);
}

activity.setContentView(layout.activity_main);
http.fetch("http://www.iheartquotes.com/api/v1/random", this);
output="Hello, Android!";

