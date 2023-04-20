import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CATEGORIES, COUNTRIES, COUNTRIES_API } from './constants';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Country } from './models';
import { Observable, filter, map, tap } from 'rxjs';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  newsForm!: FormGroup

  countryList: Country[] = []

  country$!: Observable<Country[]>
  flag = ''

  readonly countries = COUNTRIES
  readonly categories = CATEGORIES

  constructor(private fb: FormBuilder, private http: HttpClient) { }

  ngOnInit(): void {
    this.newsForm = this.createSearchForm()
    this.country$ = this.getCountries()
        .pipe(
          tap( v => this.countryList = v),
        )
  }

  getNews() {

  }

  onSelectedCountry(event: any) {
    const code = event.target.value
    const c = this.countryList.find(v => v.code == code)
    // @ts-ignore
    this.flag = c?.flag
  }

  private getCountries(): Observable<Country[]> {
    const csv = this.countries.join(',')
    const params = new HttpParams().set('codes', csv)
    return this.http.get<Country[]>(COUNTRIES_API, { params })
        .pipe(
          map((v: any[]) => {
            return v.map(c => {
              return {
                name: c['name']['common'],
                code: c['cca2'].toLowerCase(),
                flag: c['flag']
              } as Country
            })
          })
        )
  }

  private createSearchForm(): FormGroup {
    return this.fb.group({
      country: this.fb.control('', [ Validators.required ]),
      category: this.fb.control('', [ Validators.required ]),
    })
  }
}
