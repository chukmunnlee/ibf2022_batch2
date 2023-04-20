import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Weather } from './model';
import { Observable, Subscription, firstValueFrom, lastValueFrom, map } from 'rxjs';

const WEATHER_URL = 'https://api.openweathermap.org/data/2.5/weather'

// Remeber to redact your API key when you check in
const WEATHER_API_KEY = '_YOUR_OPENWEATHERMAP_KEY_'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  form!: FormGroup
  weather$!: Subscription
  weatherObs$!: Observable<Weather[]>
  weatherProm$!: Promise<Weather[]>

  result: Weather[] = []

  // remember to import HttpClientModule
  constructor(private fb: FormBuilder, private http: HttpClient) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      city: this.fb.control('', [ Validators.required ])
    })
  }

  ngOnDestroy(): void {
    this.weather$.unsubscribe()
  }

  getWeatherWithObservable() {
    this.weatherObs$ = this.getWeather()
  }

  getWeatherWithPromise2() {

    // converts the first value of the observable
    // into a promise, lastValueFrom()
    this.weatherProm$ = lastValueFrom(
      // returns an observable
      this.getWeather()
    )
  }

  getWeatherWithPromise() {

    // converts the first value of the observable
    // into a promise, lastValueFrom()
    lastValueFrom(
      // returns an observable
      this.getWeather()
    ).then(v => {
      console.info('resolved: ', v)
      this.result = v
    }).catch(err => {
      console.error('>>> error: ', err)
    })

  }

  getWeatherWithSubscription() {

    // unsubscribe first before subscribing
    if (this.weather$)
      this.weather$.unsubscribe()

    this.getWeather()
      .subscribe({
        next: v => {
          console.info('----- NEXT')
          this.result = v
        },
        error: err => {
          console.error('----- ERROR: ', err)
        },
        complete: () => {
          console.warn('----- COMPLETE: ')
        }
      })

  }

  getWeather() {
    const city = this.form.value['city']
    console.info(`City: ${city}`)

    // create query params
    const params = new HttpParams()
        .set('q', city)
        .set('units', 'metric')
        .set('appid', WEATHER_API_KEY)

    // returns an observable
    return this.http.get<Weather[]>(WEATHER_URL, { params })
      .pipe(
        map((v: any) => {
          // .main.temp
          const temp = v['main']['temp']
          // .weather
          const weather = v['weather'] as any[]
          return weather.map(w => {
            return {
              // .weather[*].main
              main: w['main'],
              // .weather[*].description
              description: w['description'],
              // .weather[*].icon
              icon: w['icon'],
              temperature: temp
            } as Weather
          })
        })
      )
  }
}
