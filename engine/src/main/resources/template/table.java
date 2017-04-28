package $!{projectPackage}.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author wangxiaodan
 *
 */
@Entity
@Table(name="$!{tableName}")
public class $!{beanName} {
	
	#foreach ($column in $tableMetaData.columnMetaDatas)
		#if ($column.fieldName == 'id')
			@Id
			@GeneratedValue(strategy = GenerationType.AUTO)
			private Long id;
		#else
			@Column(name="$!{column.columnName}",nullable=false,unique=true,length=50)
			private String $!{column.fieldName};
		#end
	#end
	
	#foreach ($column in $tableMetaData.columnMetaDatas)
		#if ($column.fieldName == 'id')
			public Long get$!{column.fieldName}() {
				return $!{column.fieldName};
			}
			public void set$!{column.fieldName}(Long $!{column.fieldName}) {
				this.$!{column.fieldName} = $!{column.fieldName};
			}
		#else
			public String get$!{column.fieldName}() {
				return $!{column.fieldName};
			}
			public void set$!{column.fieldName}(String $!{column.fieldName}) {
				this.$!{column.fieldName} = $!{column.fieldName};
			}
		#end
	#end
	
}
