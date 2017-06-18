var buffer = require('buffer');

class AuthService{
  login(creds,cb){
    var buf = new buffer.Buffer(creds.username + ':' + creds.password);
    var encodeAuth = buf.toString('base64');
    console.log(encodeAuth);

    fetch('https://api.github.com/user',{
      headers: {
        'Authorization': 'Basic' + encodeAuth
      }
    })
    .then((response) =>{
      if(response.status >= 200 && response.status < 300){
        return response;
      }

      throw {
        badCredentials: response.status == 401,
        unknownError: response.status != 401
      }
    })
    .then((response)=>{
      return response.json();
    }).then((results)=>{
      console.log(results);
      return cb({success: true});
    })
    .catch((err)=>{
      console.log('login failed: ' + err);
      return cb(err);
    });

  }
}

module.exports = new AuthService();
