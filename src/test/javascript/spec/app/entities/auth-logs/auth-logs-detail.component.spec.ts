/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyAppTestModule } from '../../../test.module';
import { Auth_logsDetailComponent } from 'app/entities/auth-logs/auth-logs-detail.component';
import { Auth_logs } from 'app/shared/model/auth-logs.model';

describe('Component Tests', () => {
    describe('Auth_logs Management Detail Component', () => {
        let comp: Auth_logsDetailComponent;
        let fixture: ComponentFixture<Auth_logsDetailComponent>;
        const route = ({ data: of({ auth_logs: new Auth_logs('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyAppTestModule],
                declarations: [Auth_logsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(Auth_logsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(Auth_logsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.auth_logs).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
