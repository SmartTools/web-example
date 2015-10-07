'use strict';

const express = require('express');
const morgan = require('morgan'); // http requests logger
const request = require('request');
const bodyParser = require('body-parser');

const app = express();

app.use(morgan('combined'));
app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded

app.set('views', './views');
app.set('view engine', 'jade');

const AS_URL = 'http://localhost:9000';

app.get('/', (req, res) => {
  res.render('index');
});

app.post('/subscribe', (req, res) => {
  console.log(req.body);
  const body = req.body;

  request({
    method: 'POST',
    url: AS_URL,
    headers: {
      'Content-Type': 'application/json'
    },
    json: true,
    body: {
      name: body.name,
      email: body.email,
      address: {
        target: 'actor://nameConverter',
        handler: 'convert'
      }
    }
  }, (err, response, body) => {
    if (err) {
      console.log(JSON.stringify(err, null, ' '));
      res.status(500).send('Something broke!');
    }

    res.send(body);
  });
});

const server = app.listen(3000, () => {
  const host = server.address().address;
  const port = server.address().port;

  console.log('Example app listening at http://%s:%s', host, port);
});