import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'tabs',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'regform',
    loadChildren: () => import('./regform/regform.module').then( m => m.RegformPageModule)
  },
  {
    path: 'profdetail',
    loadChildren: () => import('./profdetail/profdetail.module').then( m => m.ProfdetailPageModule)
  },
  {
    path: 'profile',
    loadChildren: () => import('./profile/profile.module').then( m => m.ProfilePageModule)
  },
  {
    path: 'create-job',
    loadChildren: () => import('./create-job/create-job.module').then( m => m.CreateJobPageModule)
  },
  {
    path: '',
    loadChildren: () => import('./category/category.module').then( m => m.CategoryPageModule)
  },
  {
    path: 'wait',
    loadChildren: () => import('./wait/wait.module').then( m => m.WaitPageModule)
  },
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
