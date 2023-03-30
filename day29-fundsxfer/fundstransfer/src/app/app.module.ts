import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { SuccessComponent } from './components/success.component';
import { FailedComponent } from './components/failed.component';
import {FundsTransferService} from './fundstransfer.service';
import {ReactiveFormsModule} from '@angular/forms';

const appRoutes: Routes = [
	{ path: '', component: MainComponent },
	{ path: 'success/:txId', component: SuccessComponent },
	{ path: 'failed', component: FailedComponent },
	{ path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, SuccessComponent, FailedComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, ReactiveFormsModule,
	  RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [ FundsTransferService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
