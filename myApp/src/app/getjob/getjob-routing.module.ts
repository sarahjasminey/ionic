import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GetjobPage } from './getjob.page';

const routes: Routes = [
  {
    path: '',
    component: GetjobPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GetjobPageRoutingModule {}
