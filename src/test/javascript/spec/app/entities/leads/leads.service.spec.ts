/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { LeadsService } from 'app/entities/leads/leads.service';
import { ILeads, Leads } from 'app/shared/model/leads.model';

describe('Service Tests', () => {
    describe('Leads Service', () => {
        let injector: TestBed;
        let service: LeadsService;
        let httpMock: HttpTestingController;
        let elemDefault: ILeads;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(LeadsService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Leads(
                'ID',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find('123')
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Leads', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 'ID'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Leads(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Leads', async () => {
                const returnedFromService = Object.assign(
                    {
                        salutation: 'BBBBBB',
                        fullName: 'BBBBBB',
                        firstName: 'BBBBBB',
                        lastName: 'BBBBBB',
                        dateOfBirth: 'BBBBBB',
                        emailAddress: 'BBBBBB',
                        homePhone: 'BBBBBB',
                        workPhone: 'BBBBBB',
                        cellPhone: 'BBBBBB',
                        gender: 'BBBBBB',
                        type: 'BBBBBB',
                        preferredCommsMode: 'BBBBBB',
                        stage: 'BBBBBB',
                        status: 'BBBBBB',
                        leadInterest: 'BBBBBB',
                        leadQualityScore: 'BBBBBB',
                        assignedTo: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Leads', async () => {
                const returnedFromService = Object.assign(
                    {
                        salutation: 'BBBBBB',
                        fullName: 'BBBBBB',
                        firstName: 'BBBBBB',
                        lastName: 'BBBBBB',
                        dateOfBirth: 'BBBBBB',
                        emailAddress: 'BBBBBB',
                        homePhone: 'BBBBBB',
                        workPhone: 'BBBBBB',
                        cellPhone: 'BBBBBB',
                        gender: 'BBBBBB',
                        type: 'BBBBBB',
                        preferredCommsMode: 'BBBBBB',
                        stage: 'BBBBBB',
                        status: 'BBBBBB',
                        leadInterest: 'BBBBBB',
                        leadQualityScore: 'BBBBBB',
                        assignedTo: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Leads', async () => {
                const rxPromise = service.delete('123').subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
