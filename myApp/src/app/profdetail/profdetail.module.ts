import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ProfdetailPageRoutingModule } from './profdetail-routing.module';

import { ProfdetailPage } from './profdetail.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ProfdetailPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [ProfdetailPage]
})
export class ProfdetailPageModule {}
