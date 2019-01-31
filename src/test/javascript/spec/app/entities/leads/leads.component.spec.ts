/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyAppTestModule } from '../../../test.module';
import { LeadsComponent } from 'app/entities/leads/leads.component';
import { LeadsService } from 'app/entities/leads/leads.service';
import { Leads } from 'app/shared/model/leads.model';

describe('Component Tests', () => {
    describe('Leads Management Component', () => {
        let comp: LeadsComponent;
        let fixture: ComponentFixture<LeadsComponent>;
        let service: LeadsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyAppTestModule],
                declarations: [LeadsComponent],
                providers: []
            })
                .overrideTemplate(LeadsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LeadsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LeadsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Leads('123')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.leads[0]).toEqual(jasmine.objectContaining({ id: '123' }));
        });
    });
});
