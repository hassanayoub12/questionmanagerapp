
import {
    Directive,
    ElementRef,
    HostListener,
    Input,
    OnInit,
  } from '@angular/core';
  import { ProgressHelperService } from './question.service';
  
  @Directive({
    selector: '[progressStepNext], [progressStepPrev]',
  })
  export class ProgressStepDirective implements OnInit {
    @Input('progressStepNext') next: any;
    @Input('progressStepPrev') prev: any;
  
    private methods = {
      next: false,
      prev: false,
    };
  
    @HostListener('click', ['$event']) listen(e: any) {
      this.progressHelper.eventHelper.next(this.methods);
    }
  
    constructor(
      private progressHelper: ProgressHelperService,
      private el: ElementRef<HTMLButtonElement>
    ) {}
  
    ngOnInit() {
      this.initMethods();
    }
  
    private initMethods(): void {
      if ('next' in this) {
        this.methods = {
          ...this.methods,
          next: true,
        };
      }
  
      if ('prev' in this) {
        this.methods = {
          ...this.methods,
          prev: true,
        };
      }
    }
  }