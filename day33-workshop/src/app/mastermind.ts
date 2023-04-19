import {COLOURS} from "./models"

export function generateCode(toBreak = [0, 1, 2, 3, 4, 5]): string[] {
	for (let i = 0; i < 5; i++)
		for (let j = 0; j < toBreak.length; j++) {
			const v = toBreak[j]
			const toSwap = Math.floor(Math.random() * toBreak.length)
			toBreak[j] = toBreak[toSwap]
			toBreak[toSwap] = v
		}
	return toBreak.splice(0, 4).map(v => COLOURS[v])
}
