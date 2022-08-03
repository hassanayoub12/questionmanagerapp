import { Files,Question } from "./question";
import { Injectable } from "@angular/core";
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable, Subject } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn:'root'
})
export class QuestionService{
    private apiServerUrl=environment.apiBaseUrl;

   

    constructor(private http:HttpClient){ }
    
    

    public getQuestion():Observable<Question[]>{
        return this.http.get<Question[]>(`${this.apiServerUrl}/question/all`)
    }

    public uploadData(file:File):Observable<File>{
        return this.http.post<File>(`${this.apiServerUrl}/question/upload`,file);
    }

    public addQuestion(question: Question):Observable<Question>{
        return this.http.post<Question>(`${this.apiServerUrl}/question/add`,question);
    }

    public updateFiles(filess: Files):Observable<Files>{
        return this.http.put<Files>(`${this.apiServerUrl}/file/update/file`,filess);
    }
    

    public updateQuestion(question: Question):Observable<Question>{
        return this.http.put<Question>(`${this.apiServerUrl}/question/update`,question);
    }
    public deleteQuestion(questionId:Number):Observable<void>{
        return this.http.delete<void>(`${this.apiServerUrl}/question/delete/${questionId}`);
    }

    public upload(file: File): Observable<HttpEvent<any>> {
        const formData: FormData = new FormData();
        formData.append('file', file);
        const req = new HttpRequest('POST', `${this.apiServerUrl}/question/upload`, formData, {
          reportProgress: true,
          responseType: 'json'
        });
        return this.http.request(req);
      }
}
export class ProgressHelperService {
    public eventHelper = new Subject<{ prev: boolean; next: boolean }>();
    constructor() {}
  }