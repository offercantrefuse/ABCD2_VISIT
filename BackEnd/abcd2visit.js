var express = require("express");
var mysql = require("mysql");
var bodyParser = require("body-parser");
var path = require("path");
var static = require("serve-static");
var jsonexport = require("jsonexport");
var fs = require("fs");

var app = express();
var port = process.env.PORT || 9000;
var router = express.Router();

// Configure app to use bodyParser()
app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(bodyParser.json());

app.use('/', static(path.join(__dirname, '/')));

var pool = mysql.createPool({
    connectionLimit: 100,
    host: '**** DB ******',
    user: '*******',
    password: '********',
    database: '*******',
    debug: false
});

var addResult = function (school, examiner, patientID, sex, timeStamp, age60, bp, tia, duration, diabetes, visualSx, imbalance, sensory, image, ischemic, duration2, frequency, duration2Text, frequencyText, abcd2, visit, abcd2visit, callback) {

    console.log('addResult called');

    // pool.connect 로 해야되나??
    pool.getConnection(function (err, conn) {
        if (err) {
            if (conn) {
                conn.release();
            }
            callback(err, null);
            return;
        }
        console.log('DB insert connected');

        var data = { School: school, Examiner: examiner, PatientID: patientID, Sex: sex, TimeStamp: timeStamp, Age60: age60, Bp: bp, Tia: tia, Duration: duration, Diabetes: diabetes, VisualSx: visualSx, Imbalance: imbalance, Sensory: sensory, Image: image, Ischemic: ischemic, Duration2: duration2, Frequency: frequency, Duration2Text: duration2Text, FrequencyText: frequencyText, Abcd2: abcd2, Visit: visit, Abcd2Visit: abcd2visit };

        var exec = conn.query('insert into Information set ?', data, function (err, result) {
            conn.release();
            console.log('SQL execute: ' + exec.sql);

            if (err) {
                console.log('SQL error');
                callback(err, null);
                return;
            }
            callback(null, result);
        });
    });
}

router.route('/process/send').post(function (req, res) {
    console.log('/process/send used.');
    console.log(req.body);

    var School = req.body.school || req.query.school;
    var Examiner = req.body.examiner || req.query.examiner;
    var PatientID = req.body.patientID || req.query.patientID;
    var Sex = req.body.sex || req.query.sex;
    var TimeStamp = req.body.timeStamp || req.query.timeStamp;
    var Age60 = req.body.age60 || req.query.age60;
    var Bp = req.body.bp|| req.query.bp;
    var Tia= req.body.tia|| req.query.tia;
    var Duration= req.body.duration|| req.query.duration;
    var Diabetes= req.body.diabetes|| req.query.diabetes;
    var VisualSx= req.body.visualSx|| req.query.visualSx;
    var Imbalance= req.body.imbalance|| req.query.imbalance;
    var Sensory= req.body.sensory|| req.query.sensory;
    var Image= req.body.image|| req.query.image;
    var Ischemic= req.body.ischemic|| req.query.ischemic;
    var Duration2= req.body.duration2|| req.query.duration2;
    var Frequency= req.body.frequency|| req.query.frequency;
    var Duration2Text= req.body.duration2Text|| req.query.duration2Text;
    var FrequencyText= req.body.frequencyText|| req.query.frequencyText;
    var Abcd2= req.body.abcd2|| req.query.abcd2;
    var Visit= req.body.visit|| req.query.visit;
    var Abcd2Visit= req.body.abcd2visit|| req.query.abcd2visit;
    
    if (pool) {
        addResult(School, Examiner, PatientID, Sex, TimeStamp, Age60, Bp, Tia, Duration, Diabetes, VisualSx, Imbalance, Sensory, Image, Ischemic, Duration2, Frequency, Duration2Text, FrequencyText, Abcd2, Visit, Abcd2Visit, function (err, addedResult) {
            if (err) {
                console.error('result add error: ' + err.stack);
            }
            if (addedResult) {
                console.log('data saved');
                res.json({
                    result: true
                });
            }
            else {
                console.log('data save fail')
            }
        });
    } else {
        console.log("db connect fail");
    }

});

var dbUse = function (timeStampFrom, timeStampTo, callback) {
    console.log('dbUse called');

    pool.getConnection(function (err, conn) {
        if (err) {
            if (conn) {
                conn.release();
            }
            callback(err, null);
            return;
        }
        console.log('DB select connected');

        var tablename = 'Information';
        var ntimeStampTo = new Date(timeStampTo);
        ntimeStampTo.setDate(ntimeStampTo.getDate() + 1);

        var exec = conn.query("select * from ?? where timeStamp >= ? and timeStamp <= ?", [tablename, timeStampFrom, ntimeStampTo.toISOString()], function (err, rows) {
            conn.release();
            console.log('SQL execute: ' + exec.sql);

            console.log(rows);

            var options = { encoding: 'utf8' };

            jsonexport(rows, function (err, csv) {
                if (err) return console.log(err);
                fs.writeFile('./abcd2data.csv', csv, options, function (err) {
                    if (err) {
                        console.log('Error: ' + err);
                    }
                    console.log('data excel made');
                });
            });
            if (err) {
                console.log('SQL error');
                callback(err, null);
                return;
            }
            callback(null, rows);
        });
    });
}

router.route('/abcd2go').post(function (req, res) {
    console.log("abcd2 connect")
    var timeStampFrom = req.body.timestampfrom || req.query.timestampfrom;
    var timeStampTo = req.body.timestampto || req.query.timestampto;

    if (pool) {
        dbUse(timeStampFrom, timeStampTo, function (err, addedExcel) {
            if (err) {
                console.error('excel add error: ' + err.stack);
            }
            if (addedExcel) {
                console.log('data loaded');
                res.redirect('*** AWS ***');
            }
            else {
                console.log('data load fail')
            }
        });
    }
});

app.use('/', router);

// Start server
app.listen(port, () => { console.log(`server running : ${port}`) });