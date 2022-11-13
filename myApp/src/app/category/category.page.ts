import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-category',
  templateUrl: './category.page.html',
  styleUrls: ['./category.page.scss'],
})
export class CategoryPage implements OnInit {
category:any;
  constructor(private http: HttpClient) { }

  ngOnInit() {

    this.http.get("https://teddiapp.com/app/api/ionic_champ/category")
    .subscribe((data:any)=>{
          this.category = data.data.categories;
          console.log(data);
          console.log(data.data);
        })
     

  }

}
