// create a promise
let p = new Promise(
    (resolve, reject) => {
        // perform operation
        setTimeout(
            () => {
                //console.info('>> SQL completed')
                resolve('success!')
                reject('error!!!')
            },
            2000 // 2 sec
        ) 
    }
)

p
	.then(
		v => {
			console.info(`Promise resolved: ${v}`)
			throw "ERROR!!!!"
			//return v.toUpperCase()
		}
	)
	.then(v => {
		console.info(`Second then: ${v}`)
		return v + v
	})
	.then(v => {
		console.info(`THIRD then: ${v}`)
	})
	.catch(err => {
		console.error(`Error: ${err}`)
		//throw "recovered from error"
		return "recovered from error"
	})
	.then(v => {
		console.info(`After catch: ${v}`)
	})
	.catch(err => {
		console.error(`Error 2: ${err}`)
	})

console.info("after new Promise")
