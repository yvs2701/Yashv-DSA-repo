process.stdin.resume();
process.stdin.setEncoding('utf-8');

var input_stdin = "";
var input_stdin_array = null;

process.stdin.on('data', function (chunk) {
    input_stdin += chunk;
    process.stdout.write('Received: ' + chunk)
});


function addAll(arr) {
    return new Promise((resolve, reject) => {
        try {
            const sum = arr.reduce((acc, elem) => acc + (+elem), 0); // + unary operator converts elem to a number (if it was string)
            resolve(sum);
        } catch (e) {
            reject(e);
        }
    });
}

process.stdin.on('end', (code) => {
    input_stdin_array = input_stdin.split("\s+|\n+");
    
    addAll(input_stdin_array).then(sum => {
        process.stdout.write('Sum: ' + sum);
    }).catch(e => {
        process.stdout.write('Some error occurred!!\n' + e.toString());
    }).finally(() => {
        process.stdout.write('Process exiting event with code: ' + code);
        process.exit(0);
    });
});

// ISSUE:
// process.stdin.on('end', function () { // press Ctrl + D (may not be supported in windows) to end the data entry
//     main(); // run your main code
// });

// EVEN THIS FIX DOES NOT WORK:
// process.stdin.on('SIGINT', function () { // press Ctrl + C to end the program
//     main(); // run your main code
// });
