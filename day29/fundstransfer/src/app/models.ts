export interface Transfer {
	srcAcct: string
	destAcct: string
	amount: number
}

export interface Success {
	transactionId: string
}

export interface Failed {
	message: string
}
