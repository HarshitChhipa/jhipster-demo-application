export interface ILeads {
    id?: string;
    salutation?: string;
    fullName?: string;
    firstName?: string;
    lastName?: string;
    dateOfBirth?: string;
    emailAddress?: string;
    homePhone?: string;
    workPhone?: string;
    cellPhone?: string;
    gender?: string;
    type?: string;
    preferredCommsMode?: string;
    stage?: string;
    status?: string;
    leadInterest?: string;
    leadQualityScore?: string;
    assignedTo?: string;
}

export class Leads implements ILeads {
    constructor(
        public id?: string,
        public salutation?: string,
        public fullName?: string,
        public firstName?: string,
        public lastName?: string,
        public dateOfBirth?: string,
        public emailAddress?: string,
        public homePhone?: string,
        public workPhone?: string,
        public cellPhone?: string,
        public gender?: string,
        public type?: string,
        public preferredCommsMode?: string,
        public stage?: string,
        public status?: string,
        public leadInterest?: string,
        public leadQualityScore?: string,
        public assignedTo?: string
    ) {}
}
