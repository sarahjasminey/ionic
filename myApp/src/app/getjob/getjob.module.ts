import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { GetjobPageRoutingModule } from './getjob-routing.module';

import { GetjobPage } from './getjob.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    GetjobPageRoutingModule
  ],
  declarations: [GetjobPage]
})
export class GetjobPageModule {}
