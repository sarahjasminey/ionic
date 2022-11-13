import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router,NavigationExtras } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit{
  phone: any;
  otp:any;
  id:number;
  type:any;
  NavigationExtras:any;

httpHeader = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

constructor(private http: HttpClient,private router: Router) {
 
}

sendotp(phone: number) {
  const headeroptions = {   
    'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
    
  };

  console.log(this.phone);
  var data = this.phone;
  let params = 'phone=' + data ;


  return this.http.post<any>('https://teddiapp.com/app/api/ionic_user/login_reg/',params,  {headers: headeroptions}).subscribe(data => {
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
  return this.http.post<any>('https://teddiapp.com/app/api/ionic_user/check_otp/',params,  {headers: headeroptions}).subscribe(data => {
    console.log(data.data.status);
    console.log(data.data.type);
    console.log(data.data.otp);
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
