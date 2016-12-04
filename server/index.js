var app = require('express')();

var port = process.env.PORT || 7777;

app.get('/', function (req, res) {
    res.send('# progmeth-projecdfdfdt\r\ngfgfd\r\ngfdgfdgd\r\ngfdgfd');
});

app.listen(port, function() {
    console.log('Starting node.js on port ' + port);
});
