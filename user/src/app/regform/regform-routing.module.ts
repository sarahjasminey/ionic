import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegformPage } from './regform.page';

const routes: Routes = [
  {
    path: '',
    component: RegformPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RegformPageRoutingModule {}
