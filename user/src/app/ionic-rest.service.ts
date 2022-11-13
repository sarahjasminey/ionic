import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
// export class Student {
//   id: string;
//   name: string;
//   email: string;
//   dob: number;
//   fees: number;
// }
@Injectable({
  providedIn: 'root'
})
export class IonicRestService {
  httpHeader = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
    this.authenticate('123');
  }

  authenticate(phone: string) {
    const headeroptions = {
      'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
    };
  
    let params =  { emailOrMobile: phone};



    return this.http.post<any>('https://teddiapp.com/app/api/champs', params, {headers: headeroptions}).subscribe(data => {
      console.log("Subscribed Data: ");
      console.log(data);
    },
    error => {
      console.log('error: ' + error.error);
      console.log('Name: ' + error.name);
      console.log('Message: ' + error.message);
      console.log('Status: ' + error.status);
    }); 
  }



  // addStudent(student: Student): Observable<any> {
  //   return this.http.post<Student>('api-goes-here/', student, this.httpHeader)
  //     .pipe(
  //       catchError(this.handleError<Student>('Add Student'))
  //     );
  // }
  // getStudent(id): Observable<Student[]> {
  //   return this.http.get<Student[]>('api-goes-here/' + id)
  //     .pipe(
  //       tap(_ => console.log(`Student fetched: ${id}`)),
  //       catchError(this.handleError<Student[]>(`Get student id=${id}`))
  //     );
  // }
  // getStudentList(): Observable<Student[]> {
  //   return this.http.get<Student[]>('api-goes-here/')
  //     .pipe(
  //       tap(Student => console.log('Student fetched!')),
  //       catchError(this.handleError<Student[]>('Get student', []))
  //     );
  // }
  // updateStudent(id, student: Student): Observable<any> {
  //   return this.http.put('api-goes-here/' + id, student, this.httpHeader)
  //     .pipe(
  //       tap(_ => console.log(`Student updated: ${id}`)),
  //       catchError(this.handleError<Student[]>('Update student'))
  //     );
  // }
  // deleteStudent(id): Observable<Student[]> {
  //   return this.http.delete<Student[]>('api-goes-here/' + id, this.httpHeader)
  //     .pipe(
  //       tap(_ => console.log(`Student deleted: ${id}`)),
  //       catchError(this.handleError<Student[]>('Delete student'))
  //     );
  // }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}