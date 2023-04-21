const fredName = 'fred'
const fredEmail = 'fred@gmail.com'

const nameKey = 'myName'

const obj = { 
	[nameKey]: fredName, 
	email: fredEmail 
}

for (let k in obj)
	console.info(`key = ${k}, value = ${obj[k]}`)
