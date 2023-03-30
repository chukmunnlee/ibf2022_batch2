import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {firstValueFrom} from "rxjs";
import {Success, Transfer} from "./models";

@Injectable()
export class FundsTransferService {

	constructor(private http: HttpClient) { }

	transfer(tx: Transfer): Promise<Success> {
		return firstValueFrom(
			this.http.post<Success>('/api/transfer', tx)
		)
	}

}
