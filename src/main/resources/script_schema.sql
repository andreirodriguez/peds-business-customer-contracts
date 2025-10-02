IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[contract_signatory]') AND type in (N'U'))
DROP TABLE [dbo].[contract_signatory]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[contract]') AND type in (N'U'))
DROP TABLE [dbo].[contract]
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[customer]') AND type in (N'U'))
DROP TABLE [dbo].[customer]
GO

CREATE TABLE [customer] (
  [id] INT PRIMARY KEY IDENTITY(1, 1),
  [document_type] INT NOT NULL,
  [document_number] VARCHAR(16) NOT NULL,
  [name] VARCHAR(256) NOT NULL,
  [last_name] VARCHAR(256) NOT NULL,
  [country] VARCHAR(8) NOT NULL,
  [state] VARCHAR(256) NOT NULL,
  [zip_code] VARCHAR(256) NOT NULL,
  [address_fiscal] VARCHAR(1024) NOT NULL,
  [active] BIT NOT NULL
)
GO

CREATE TABLE [contract] (
  [id] INT PRIMARY KEY IDENTITY(1, 1),
  [customer_id] INT NOT NULL,
  [code] VARCHAR(32) NOT NULL,
  [version] VARCHAR(16) NOT NULL,
  [effective_date] DATETIME NOT NULL,
  [end_date] DATETIME NOT NULL,
  [product_type] VARCHAR(16) NOT NULL,
  [product] VARCHAR(256) NOT NULL,
  [operation_code] VARCHAR(16) NOT NULL,
  [organization_document_type] INT NOT NULL,
  [organization_document_number] VARCHAR(16) NOT NULL,
  [summary] VARCHAR(MAX) NOT NULL,
  [proposal] VARCHAR(MAX) NOT NULL,
  [clauses] VARCHAR(MAX) NOT NULL,
  [full_address_fiscal] VARCHAR(MAX) NOT NULL,
  [state] VARCHAR(32) NOT NULL
)
GO

CREATE TABLE [contract_signatory] (
  [id] INT PRIMARY KEY IDENTITY(1, 1),
  [contract_id] INT NOT NULL,
  [signatory_document_type] INT NOT NULL,
  [signatory_document_number] VARCHAR(16) NOT NULL,
  [state] VARCHAR(32) NOT NULL,
  [active] BIT NOT NULL
)
GO

CREATE INDEX [ik_customer_document_type] ON [customer] ("document_type")
GO

CREATE INDEX [ik_customer_document_number] ON [customer] ("document_number")
GO

CREATE INDEX [ik_customer_active] ON [customer] ("active")
GO

CREATE INDEX [ik_contract_code] ON [contract] ("code")
GO

CREATE INDEX [ik_contract_version] ON [contract] ("version")
GO

CREATE INDEX [ik_contract_state] ON [contract] ("state")
GO

CREATE INDEX [ik_contract_signatory_document_type] ON [contract_signatory] ("signatory_document_type")
GO

CREATE INDEX [ik_contract_signatory_document_number] ON [contract_signatory] ("signatory_document_number")
GO

CREATE INDEX [ik_contract_signatory_state] ON [contract_signatory] ("state")
GO

CREATE INDEX [ik_contract_signatory_active] ON [contract_signatory] ("active")
GO

ALTER TABLE [contract] ADD CONSTRAINT [fk_contract_customer_id] FOREIGN KEY ([customer_id]) REFERENCES [customer] ([id])
GO

ALTER TABLE [contract_signatory] ADD CONSTRAINT [fk_contract_signatory_contract_id] FOREIGN KEY ([contract_id]) REFERENCES [contract] ([id])
GO
INSERT INTO dbo.customer
(document_type,document_number,name,last_name,country,[state],zip_code,address_fiscal,active)
VALUES(1,'44136002','Andrei','Rodriguez','+051','LIMA','15096','Av. Defensores del Morro 1868',1)
GO
INSERT INTO dbo.customer
(document_type,document_number,name,last_name,country,[state],zip_code,address_fiscal,active)
VALUES(1,'90136003','Ruben','Dominguez','+051','LIMA','15096','Av. Defensores del Morro 1868',1)
GO