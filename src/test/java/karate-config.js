function fn(){
  // set our values to expose as global vars to our Karate scripts
  karate.log('Configuring...');
  
  var config = { // base config JSON
    baseUrl: 'http://localhost:8080/demo'
  };

  // don't waste time waiting for a connection or if servers don't respond within 5 seconds
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  
  karate.log('Configuration complete.');
  
  return config;
}
