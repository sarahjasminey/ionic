import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router,NavigationExtras } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit{
  mobile: any;
  otp:any;
  id:number;
  type:any;
  NavigationExtras:any;

httpHeader = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

constructor(private http: HttpClient,private router: Router) {
 
}

sendotp(mobile: number) {
  const headeroptions = {   
    'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
    
  };

  console.log(this.mobile);
  var data = this.mobile;
  let params = 'phone=' + data ;


  return this.http.post<any>('https://teddiapp.com/app/api/ionic_champ/login_reg/',params,  {headers: headeroptions}).subscribe(data => {
    console.log("Subscribed Data: ");
    console.log(data);
    this.id=data.data.id;
    this.type=data.data.type;

    
  },
  error => {
    console.log('error: ' + error.error);
    console.log('Name: ' + error.name);
    console.log('Message: ' + error.message);
    console.log('Status: ' + error.status);
  }); 
}

verifyotp(){
  const headeroptions = {   
    'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
    
  };
  console.log(this.otp);
  let params = 'otp=' + this.otp + '&id=' + this.id + '&type=' + this.type;
  console.log(params);
  return this.http.post<any>('https://teddiapp.com/app/api/ionic_champ/check_otp/',params,  {headers: headeroptions}).subscribe(data => {
    console.log(data.data.status);
    console.log(data.data.type);
    if(data.data.status=='success'){
      // this.router.navigateByUrl('regform');
      let navigationExtras: NavigationExtras = {
        queryParams: {
          type: this.type,
          id:this.id
        }
      };
      this.router.navigate(['regform'], navigationExtras);
  

    }
    else{
      alert('otp not verified');
    }
    
  },
  error => {
    console.log('error: ' + error.error);
    console.log('Name: ' + error.name);
    console.log('Message: ' + error.message);
    console.log('Status: ' + error.status);
  }); 




}

ngOnInit(): void {
  
}


}
