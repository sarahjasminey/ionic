import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { RegformPageRoutingModule } from './regform-routing.module';

import { RegformPage } from './regform.page';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RegformPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [RegformPage]
})
export class RegformPageModule {



}
