import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CATEGORIES, COUNTRIES, COUNTRIES_API } from './constants';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Article, Country } from './models';
import { Observable, filter, map, tap } from 'rxjs';
import { NewsService } from './services/news.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  newsForm!: FormGroup

  countryList: Country[] = []

  country$!: Observable<Country[]>
  news$!: Promise<Article[]>

  flag = ''

  readonly countries = COUNTRIES
  readonly categories = CATEGORIES

  constructor(private fb: FormBuilder, private http: HttpClient
      , private newsSvc: NewsService) { }

  ngOnInit(): void {
    this.newsForm = this.createSearchForm()
    this.country$ = this.newsSvc.getCountries()
        .pipe(
          tap( v => this.countryList = v),
        )
  }

  getNews() {
    const country = this.newsForm.value['country']
    const category = this.newsForm.value['category']
    this.news$ = this.newsSvc.getNews(country, category)
  }

  onSelectedCountry(event: any) {
    const code = event.target.value
    const c = this.countryList.find(v => v.code == code)
    // @ts-ignore
    this.flag = c?.flag
  }

  private createSearchForm(): FormGroup {
    return this.fb.group({
      country: this.fb.control('', [ Validators.required ]),
      category: this.fb.control('', [ Validators.required ]),
    })
  }
}
